/**********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                             *
 * @CreatedDate           : 2022-11-23 20:45:00                                                                       *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                             *
 * @LastEditDate          : 2023-04-08 23:01:30                                                                       *
 * @FilePath              : src/main/java/com/da/sage/assistant/service/SageService.java                              *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                           *
 *********************************************************************************************************************/

package com.da.sage.assistant.service;

import com.alibaba.fastjson.JSONObject;
import com.da.sage.assistant.utils.SageActionHelper;
import com.da.sage.assistant.utils.SageActionHelper.Action;
import com.da.sage.assistant.utils.SageActionHelper.MsgTyp;
import com.google.common.cache.*;
import com.microsoft.sqlserver.jdbc.StringUtils;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class SageService {

    private static final Logger log = LogManager.getLogger();

    /**
     * google guava cache
     * Key is Object {auth: "xxx", function: "xxxx", trans: "xxxxx"}
     * Value is Object {success: true, SessionId: id, xid: "AA5", rcdNO: RecNO, msgTyp: "info", msg: "Login"}
     *
     */
    private static LoadingCache<JSONObject, JSONObject> sageSessionCache = CacheBuilder
        .newBuilder()
        .maximumSize(10000 * 3)
        .expireAfterAccess(5, TimeUnit.MINUTES)
        .removalListener(
            new RemovalListener<JSONObject, JSONObject>() {
                public void onRemoval(RemovalNotification<JSONObject, JSONObject> removal) {
                    /*
                     * Sage will expired created session automatically, we exit page to avoid this
                     */
                    JSONObject key = removal.getKey();
                    JSONObject val = removal.getValue();
                    String auth = key.getString("auth");
                    String sessionId = val.getString("SessionId");

                    log.debug("Sage SessionId {} is removed, cause is {}", sessionId, removal.getCause());

                    endSession(auth, sessionId);
                }
            }
        )
        .build(
            new CacheLoader<JSONObject, JSONObject>() {
                @Override
                public JSONObject load(JSONObject key) {
                    log.debug("load key: {}", key);

                    String auth = key.getString("auth");
                    String function = key.getString("function");
                    String trans = key.getString("trans");

                    return getSageSession(auth, function, trans);
                }
            }
        );

    /**
     * Internal method to get Sage Session, using getSafeSessionCache to get session from cache
     */
    private static JSONObject getSageSession(String auth, String function, String trans) {
        HttpResponse<String> response = HttpService.request(
            String.format(
                "https://192.168.10.62/trans/x3/erp/EXPLOIT/$sessions?f=%s/2//M&trackngId=%s",
                function,
                UUID.randomUUID().toString()
            ),
            "POST",
            "{\"settings\":{}}",
            auth
        );
        String html = response.body();
        JSONObject json = JSONObject.parseObject(html);

        // Sage Server performance low, header contains `location`,
        // Using GET to get the response by `location`, continue
        if (isPerformanceLow(json)) {
            String location = response.headers().firstValue("location").orElse("");
            json = getTrackingResponse(auth, location);
        }

        JSONObject rtn = new JSONObject();
        if (json.containsKey("srvop") && json.getJSONObject("srvop").containsKey("sessionInfo")) {
            String sessionId = json
                .getJSONObject("srvop")
                .getJSONObject("sessionInfo")
                .getJSONObject("node")
                .getString("sid");

            rtn = SageActionHelper.rtnObj(true, MsgTyp.INFO, "Session Success");
            rtn.put("SessionId", sessionId);

            // no trans
            if (trans == null || trans.isEmpty() || trans.isBlank()) {
                // get RecorderNO position
                String xid = json.getJSONObject("sap").getJSONObject("target").getJSONObject("ist").getString("xid");
                // get RecorderNO value
                // ❗❗❗❗❗ Some times Sage Server will return bad content, it doesn't contains recorder
                try {
                    String rcdNO = json
                        .getJSONObject("sap")
                        .getJSONObject("wins")
                        .getJSONObject("B")
                        .getJSONObject("entities")
                        .getJSONObject(xid)
                        .getString("v");

                    rtn.put("xid", xid);
                    rtn.put("rcdNO", rcdNO);
                    return rtn;
                } catch (Exception e) {
                    return SageActionHelper.rtnObj(false, MsgTyp.ERROR, "Session and trans Failed");
                }
            }
            // with trans
            else {
                String select = SageActionHelper.popSel("B", "bA", 0, trans);
                JSONObject rtn2 = doSageAct(auth, function, trans, sessionId, select, null);

                if (rtn2.getBoolean("success")) {
                    rtn = SageActionHelper.rtnObj(true, MsgTyp.INFO, "Session and trans Success");
                    rtn.put("SessionId", sessionId);

                    // msg is content
                    JSONObject json2 = JSONObject.parseObject(rtn2.getString("msg"));
                    // get RecorderNO position
                    String xid = json2
                        .getJSONObject("sap")
                        .getJSONObject("target")
                        .getJSONObject("ist")
                        .getString("xid");

                    // get RecorderNO value
                    // ❗❗❗❗❗ Some times Sage Server will return bad content, it doesn't contains recorder
                    try {
                        String rcdNO = json2
                            .getJSONObject("sap")
                            .getJSONObject("wins")
                            .getJSONObject("B")
                            .getJSONObject("entities")
                            .getJSONObject(xid)
                            .getString("v");

                        rtn.put("xid", xid);
                        rtn.put("rcdNO", rcdNO);
                        return rtn;
                    } catch (Exception e) {
                        return SageActionHelper.rtnObj(false, MsgTyp.ERROR, "Session and trans Failed");
                    }
                } else {
                    return SageActionHelper.rtnObj(false, MsgTyp.ERROR, "Session and trans Failed");
                }
            }
        }
        // without session info
        else {
            log.error("Unknown response: {} ", json.toJSONString());
            rtn = SageActionHelper.rtnObj(false, MsgTyp.ERROR, "Unknown response");
            return rtn;
        }
    }

    /**
     * If success, `rtn.msg` is content
     */
    private static JSONObject doSageAct(
        String auth,
        String function,
        String trans,
        String session,
        String data,
        String preTacking
    ) {
        return doSageAct(auth, function, trans, session, data, preTacking, false);
    }

    /**
     * If success, `rtn.msg` is content
     */
    private static JSONObject doSageAct(
        String auth,
        String function,
        String trans,
        String session,
        String data,
        String preTacking,
        boolean isAsync
    ) {
        String uuid = preTacking != null ? preTacking : UUID.randomUUID().toString();
        HttpResponse<String> response = HttpService.request(
            String.format(
                "https://192.168.10.62/trans/x3/erp/EXPLOIT/$sessions('%s')/requestSvc?act=%s&trackngId=%s",
                session,
                JSONObject.parseObject(data).getString("act"),
                uuid
            ),
            "PUT",
            data,
            auth,
            isAsync
        );
        String html = response.body();
        JSONObject json = JSONObject.parseObject(html);

        // Sage Server performance low, header contains `location`,
        // Using GET to get the response by `location`, continue
        if (isPerformanceLow(json)) {
            String location = response.headers().firstValue("location").orElse("");
            json = getTrackingResponse(auth, location);
        }

        JSONObject sap = json.getJSONObject("sap");
        JSONObject target = sap != null ? sap.getJSONObject("target") : null;

        JSONObject rtn = new JSONObject();

        if (target != null) {
            String targetType = target.getString("type");

            switch (targetType) {
                case "ist":
                    // ist means success, without pop window
                    rtn =
                        SageActionHelper.rtnObj(
                            true,
                            MsgTyp.INFO,
                            Optional.ofNullable(json).orElse(new JSONObject()).toJSONString()
                        );

                    break;
                case "box":
                    JSONObject box = target.getJSONObject("box");
                    Integer boxType = box.getInteger("type");

                    switch (boxType) {
                        case 1:
                            // type 1 means info
                            rtn = SageActionHelper.rtnObj(true, MsgTyp.INFO, box.getString("li"));
                            break;
                        case 2:
                            // 2 means success, with pop window, need to select
                            rtn =
                                SageActionHelper.rtnObj(
                                    true,
                                    MsgTyp.QUESTION,
                                    Optional.ofNullable(json).orElse(new JSONObject()).toJSONString()
                                );

                            break;
                        case 3:
                            // 3 means warning
                            rtn = SageActionHelper.rtnObj(false, MsgTyp.WARN, box.getString("li"));
                            break;
                        default:
                            rtn = SageActionHelper.rtnObj(false, MsgTyp.ERROR, "Unknown response");
                            break;
                    }
                    break;
                case "portal":
                    // portal means login expired or exit page
                    if (
                        Optional.ofNullable(sap).orElse(new JSONObject()).containsKey("func") &&
                        Optional.ofNullable(sap).orElse(new JSONObject()).getJSONObject("func").containsKey("close")
                    ) {
                        rtn = SageActionHelper.rtnObj(true, MsgTyp.INFO, "Page exit");
                    } else {
                        rtn = SageActionHelper.rtnObj(true, MsgTyp.ERROR, "Session expired");
                    }
                    break;
                default:
                    rtn = SageActionHelper.rtnObj(false, MsgTyp.ERROR, "Unknown response");
            }
            // } else if (srvop != null && diagnosis != null) {
            //     rtn = SageActionHelper.rtnObj(false, MsgTyp.ERROR, diagnosis.getString("$message"));
        } else if (json.containsKey("$message")) {
            log.debug(Optional.ofNullable(json.getString("$details")).orElse("Unknown response"));
            rtn = SageActionHelper.rtnObj(false, MsgTyp.ERROR, json.getString("$message"));
        } else {
            rtn = SageActionHelper.rtnObj(false, MsgTyp.ERROR, "Unknown response");
        }

        return rtn;
    }

    /**
     * contains `phase`, `Tracking`, and status is 202
     */
    private static boolean isPerformanceLow(JSONObject json) {
        // only adjust body is enough
        if (json.containsKey("phase") && json.getString("phase").equals("Tracking")) {
            log.warn("Sage Server performance low");
            return true;
        }
        return false;
    }

    /**
     * In case of server performance low, sleep 0.5s, then get response by GET
     * @param auth
     * @param location without schemas and hosts
     * @return
     */
    private static JSONObject getTrackingResponse(String auth, String location) {
        log.debug(location);

        // Sleep 0.5 seconds
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String html = HttpService.request("https://192.168.10.62" + location, "GET", null, auth).body();
        JSONObject json = JSONObject.parseObject(html);
        return isPerformanceLow(json) ? getTrackingResponse(auth, location) : json;
    }

    public static JSONObject getSageSessionCache(String auth, String function, String trans) {
        JSONObject key = new JSONObject();
        key.put("auth", auth);
        key.put("function", function);
        key.put("trans", trans);
        try {
            return sageSessionCache.get(key);
        } catch (ExecutionException e) {
            log.error(e.getMessage());
            return SageActionHelper.rtnObj(false, MsgTyp.ERROR, "Get session from cache failed");
        }
    }

    public static JSONObject doLogin(String auth) {
        try {
            String html = HttpService.request("https://192.168.10.62/auth/login/submit", "POST", "{}", auth).body();
            JSONObject json = JSONObject.parseObject(html).getJSONArray("$diagnoses").getJSONObject(0);

            String severity = json.getString("$severity");
            switch (severity) {
                case "info":
                    return SageActionHelper.rtnObj(true, MsgTyp.INFO, "Login success");
                case "error":
                    return SageActionHelper.rtnObj(false, MsgTyp.ERROR, json.getString("$message"));
                default:
                    return SageActionHelper.rtnObj(false, MsgTyp.ERROR, "Login failed");
            }
        } catch (Exception e) {
            log.error("Login Exception: {}", e.getMessage());
            return SageActionHelper.rtnObj(false, MsgTyp.ERROR, "Login failed");
        }
    }

    public static JSONObject doLogout(String auth) {
        try {
            // end all session first
            for (var entry : sageSessionCache.asMap().entrySet()) {
                JSONObject key = entry.getKey();
                JSONObject val = entry.getValue();
                String auth2 = key.getString("auth");
                String sessionId = val.getString("SessionId");

                if (auth2.equals(auth)) {
                    endSession(auth, sessionId);
                }
            }

            String html = HttpService.request("https://192.168.10.62/logout", "POST", null, auth).body();
            JSONObject json = JSONObject.parseObject(html).getJSONArray("$diagnoses").getJSONObject(0);

            String severity = json.getString("$severity");
            switch (severity) {
                case "success":
                    return SageActionHelper.rtnObj(true, MsgTyp.INFO, "Logout success");
                case "error":
                    return SageActionHelper.rtnObj(false, MsgTyp.ERROR, json.getString("$message"));
                default:
                    return SageActionHelper.rtnObj(false, MsgTyp.ERROR, "Logout failed");
            }
        } catch (Exception e) {
            log.error("Logout Exception: {}", e.getMessage());
            return SageActionHelper.rtnObj(false, MsgTyp.ERROR, "Logout failed");
        }
    }

    public static JSONObject endSession(String auth, String sessionId) {
        String html = HttpService
            .request(
                String.format("https://192.168.10.62/trans/x3/erp/EXPLOIT/$sessions('%s')", sessionId),
                "DELETE",
                null,
                auth
            )
            .body();

        JSONObject json = JSONObject.parseObject(html).getJSONArray("$diagnoses").getJSONObject(0);

        String severity = json.getString("$severity");
        switch (severity) {
            case "info":
                return SageActionHelper.rtnObj(true, MsgTyp.INFO, json.getString("$message"));
            default:
                return SageActionHelper.rtnObj(false, MsgTyp.ERROR, "End session failed");
        }
    }

    public static JSONObject getProfile(String auth) {
        try {
            String html = HttpService
                .request(
                    "https://192.168.10.62/sdata/syracuse/collaboration/syracuse/userProfiles/$template/$workingCopies",
                    "POST",
                    "{\"representation\": \"userProfile.$edit\"}",
                    auth
                )
                .body();

            JSONObject user = JSONObject.parseObject(html).getJSONObject("user");
            JSONObject selectedLocale = JSONObject.parseObject(html).getJSONObject("selectedLocale");

            JSONObject rtn = new JSONObject();
            rtn.put("userId", user.getString("$key"));
            rtn.put("userName", user.getString("firstName") + " " + user.getString("lastName"));
            rtn.put("email", user.getString("email"));
            rtn.put("language", selectedLocale.getString("code"));

            return SageActionHelper.rtnObj(true, MsgTyp.INFO, rtn.toJSONString());
        } catch (Exception e) {
            log.error("getProfile Exception: {}", e.getMessage());
            return SageActionHelper.rtnObj(false, MsgTyp.ERROR, "GetProfile failed");
        }
    }

    public static JSONObject getFunction(String auth) {
        try {
            String html = HttpService
                .request(
                    "https://192.168.10.62/sdata/syracuse/collaboration/syracuse/pages('x3.erp.EXPLOIT.home.$navigation,$page,')",
                    "GET",
                    null,
                    auth
                )
                .body();
            // regex style:   "convergenceFunction":"GESSOH"
            Pattern pattern = Pattern.compile("(?:\"convergenceFunction\":\")(.*?)(?:\")");
            Matcher m = pattern.matcher(html);
            List<String> list = new ArrayList<String>();
            while (m.find()) {
                String group = m.group();
                list.add(group.substring(22, group.length()));
            }

            return SageActionHelper.rtnObj(true, MsgTyp.INFO, list.toString());
        } catch (Exception e) {
            log.error("getFunction Exception: {}", e.getMessage());
            return SageActionHelper.rtnObj(false, MsgTyp.ERROR, "[]");
        }
    }

    /*
     * Return print Report UUID
     */
    private static JSONObject doActPrint(
        String auth,
        String function,
        String trans,
        String sessionId,
        String xid,
        String val,
        String rptSelect
    ) {
        // Step one: go to recorder
        JSONObject rtn = doSageAct(auth, function, trans, sessionId, SageActionHelper.set("B", xid, 0, val), null);
        if (!rtn.getBooleanValue("success")) {
            if (rtn.getString("msgTyp").equals("WARN")) {
                // rewrite message, original message is ""
                rtn.put("msg", "Recorder not found");
            }
            return rtn;
        }

        // Step two: go to print page
        rtn = doSageAct(auth, function, trans, sessionId, SageActionHelper.act(Action.ENTER_PRINT_PAGE), null);
        if (!rtn.getBoolean("success")) {
            return rtn;
        }

        // Step three: select print type, if val2 is null, then select default
        if (rptSelect != null && !rptSelect.isEmpty() && !rptSelect.isBlank()) {
            rtn = doSageAct(auth, function, trans, sessionId, SageActionHelper.popSel("C", "bA", 0, rptSelect), null);
            if (!rtn.getBoolean("success")) {
                return rtn;
            }
        }

        // Step four: print
        rtn = doSageAct(auth, function, trans, sessionId, SageActionHelper.act(Action.PRINT), null);
        if (!rtn.getBoolean("success")) {
            return rtn;
        }

        // Step five: get uuid
        JSONObject obj = JSONObject.parseObject(rtn.getString("msg"));
        String uuid = obj
            .getJSONObject("sap")
            .getJSONObject("jobs")
            .getJSONObject("report")
            .getJSONArray("submitted")
            .getJSONObject(0)
            .getString("uuid");

        rtn.put("msg", uuid);

        return rtn;
    }

    public static JSONObject getReportUUID(String auth, String report, String reportNO, String opt) {
        String function = SageActionHelper.getFunction(report);

        // trans
        String trans =
            switch (function) {
                case "GESSOH" -> SageActionHelper.getSalesOrderTransaction(reportNO);
                case "GESSIH" -> SageActionHelper.getInvoiceTransaction(reportNO);
                case "GESPOH" -> SageActionHelper.getPurchaseTransaction(reportNO);
                default -> "";
            };

        String rptSelect =
            switch (function) {
                case "GESSDH" -> "7~1:BONLIV!1";
                case "GESPOH" -> (opt == null || opt.isEmpty()) ? "7~1:BONTTC2!1" : "7~1:BONCDE2!1";
                default -> "";
            };

        JSONObject session = getSageSessionCache(auth, function, trans);
        //if is not successful, return it
        if (!session.getBooleanValue("success")) {
            return session;
        }
        String sessionId = session.getString("SessionId");
        String reportNOField = session.getString("xid");

        // rtn.msg is uuid
        JSONObject rtn = doActPrint(auth, function, trans, sessionId, reportNOField, reportNO, rptSelect);
        return rtn;
    }

    public static JSONObject updateSageField(
        String auth,
        String targetMain,
        String targetMainNO,
        Integer modLine,
        String targetSub,
        String targetSubVal
    ) {
        String function = SageActionHelper.getFunction(targetMain);

        // trans
        String trans =
            switch (function) {
                case "GESSOH" -> SageActionHelper.getSalesOrderTransaction(targetMainNO);
                case "GESSIH" -> SageActionHelper.getInvoiceTransaction(targetMainNO);
                case "GESPOH" -> SageActionHelper.getPurchaseTransaction(targetMainNO);
                default -> "";
            };

        JSONObject session = getSageSessionCache(auth, function, trans);
        //if is not successful, return it
        if (!session.getBooleanValue("success")) {
            return session;
        }

        String sessionId = session.getString("SessionId");
        String targetMainField = session.getString("xid");
        String rcdNO = session.getString("rcdNO");

        // step one: go to target recorder
        log.debug("go to target recorder >>>");
        JSONObject rtn = doSageAct(
            auth,
            function,
            trans,
            sessionId,
            SageActionHelper.set("B", targetMainField, 0, targetMainNO),
            null
        );
        if (!rtn.getBooleanValue("success")) {
            if (rtn.getString("msgTyp").equals("WARN")) {
                // rewrite message, original message is ""
                rtn.put("msg", "Recorder not found");
            }
            return rtn;
        }
        log.debug("go to target recorder <<<");

        // step two: try to edit
        log.debug("edit model >>>");
        rtn = doSageAct(auth, function, trans, sessionId, SageActionHelper.goTo("B", targetSub, modLine), null);
        if (!rtn.getBooleanValue("success")) {
            return rtn;
        }
        log.debug("edit model <<<");

        // step three: always no revise
        if (rtn.getBooleanValue("success") && rtn.getString("msgTyp").equals("QUESTION")) {
            log.debug("always no revise >>>");
            rtn = doSageAct(auth, function, trans, sessionId, SageActionHelper.noRevise(), null);
            log.debug("always no revise <<<");
        }

        // step four: modify and save, based on the value type
        // If someone else opened target, edit mode will return false, step four will be skipped.
        if (rtn.getBooleanValue("success")) {
            Integer iVal = -1;

            if (StringUtils.isInteger(targetSubVal)) {
                iVal = Integer.valueOf(targetSubVal);
            }

            log.debug("modify and save >>>");
            // ❗❗❗❗❗ Limit integer value, assume that the value is list option val
            if (iVal > 0 && iVal < 10) {
                rtn =
                    doSageAct(
                        auth,
                        function,
                        trans,
                        sessionId,
                        SageActionHelper.save("B", targetSub, modLine, iVal),
                        null
                    );
            } else {
                rtn =
                    doSageAct(
                        auth,
                        function,
                        trans,
                        sessionId,
                        SageActionHelper.save("B", targetSub, modLine, targetSubVal),
                        null
                    );
            }
            log.debug("modify and save <<<");
        }

        // Finally, we goto the default record, here could not await, make response time less to user
        log.debug("goto the default record >>>");
        doSageAct(auth, function, trans, sessionId, SageActionHelper.set("B", targetMainField, 0, rcdNO), null, true);
        log.debug("goto the default record <<<");

        // return
        return rtn;
    }
}
