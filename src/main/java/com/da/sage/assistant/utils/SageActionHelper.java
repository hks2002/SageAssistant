/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2023-02-19 20:31:38                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-04-03 01:13:49                                                                      *
 * @FilePath              : src/main/java/sageassistant/dataSrv/utils/SageActionHelper.java                          *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.utils;

import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SageActionHelper {

    private static final Logger log = LogManager.getLogger();

    public enum MsgTyp {
        INFO(0),
        QUESTION(1),
        WARN(2),
        ERROR(3);

        private Integer value;

        MsgTyp(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }

    public enum Action {
        SELECT_LIST(1052),
        SEARCH(782),
        ENTER_PRINT_PAGE(2820),
        PRINT(2125),
        REVISE_MAIN(1044),
        JUMP_TO(774),
        INPUT_ENTER(1028),
        INPUT_TAB(1025),
        CANCEL_MODIFY(2816),
        REFRESH_PAGE(2823),
        EXIT_PAGE(2845),
        SAVE(1116);

        private Integer value;

        Action(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }

    static HashMap<String, String> rptMap = new HashMap<String, String>() {
        {
            put("SalesOrder", "GESSOH");
            put("Delivery", "GESSDH");
            put("PurchaseOrder", "GESPOH");
            put("Invoice", "GESSIH");
        }
    };

    /**
     * Only for act, such as click some button
     * If want to change/select before this action, consider use `sel`/`set`
     * @param act
     * @return
     */
    public static String act(SageActionHelper.Action act) {
        return String.format("""
                {"act":%d,"tech":{}}
                        """, act.value);
    }

    /**
     * Set target data
     */
    public static String set(String win, String xid, Integer nl, String val) {
        return String.format(
            """
            {
                "act":%d,
                "fld":{
                        "st":{"win":"%s","xid":"%s","nl":%d},
                        "fmtKind":"EDIT","ctx":{},
                        "notModified":false,"v":"%s"
                },
                "tech":{}
            }
            """,
            Action.INPUT_TAB.value,
            win,
            xid,
            nl,
            val
        );
    }

    /**
     * Set target data
     */
    public static String set(String win, String xid, Integer nl, Integer val) {
        return String.format(
            """
            {
                "act":%d,
                "fld":{
                        "st":{"win":"%s","xid":"%s","nl":%d},
                        "fmtKind":"EDIT","ctx":{},
                        "notModified":false,"v":%d
                },
                "tech":{}
            }
            """,
            Action.INPUT_TAB.value,
            win,
            xid,
            nl,
            val
        );
    }

    /**
     * Set target data, then save
     */
    public static String save(String win, String xid, Integer nl, String val) {
        return String.format(
            """
            {
                "act":%d,
                "fld":{
                        "st":{"win":"%s","xid":"%s","nl":%d},
                        "fmtKind":"EDIT","ctx":{},
                        "notModified":false,"v":"%s"
                },
                "tech":{}
            }
            """,
            Action.SAVE.value,
            win,
            xid,
            nl,
            val
        );
    }

    /**
     * Set target data, then save
     */
    public static String save(String win, String xid, Integer nl, Integer val) {
        return String.format(
            """
            {
                "act":%d,
                "fld":{
                        "st":{"win":"%s","xid":"%s","nl":%d},
                        "fmtKind":"EDIT","ctx":{},
                        "notModified":false,"v":%d
                },
                "tech":{}
            }
            """,
            Action.SAVE.value,
            win,
            xid,
            nl,
            val
        );
    }

    /**
     * Select pop list target
     */
    public static String popSel(String win, String xid, Integer nl, String val) {
        // ACT.SELECT_LIST 1052, std: ["2~2"] without input
        // ACT.SEARCH 782, sudo: [["v1","v2"],null]
        String s =
            """
            {
                "act":%d,
                "param":{
                    "target":{"win":"%s","xid":"%s","nl":%d},
                    "std":["%s"]
                },
                "tech":{}
            }
            """;

        return String.format(s, Action.SELECT_LIST.value, win, xid, nl, val);
    }

    /**
     * Search list target
     */
    public static String searchSel(String win, String xid, Integer nl, String val) {
        // ACT.SELECT_LIST 1052, std: ["2~2"] without input
        // ACT.SEARCH 782, sudo: [["v1","v2"],null]
        String s =
            """
            {
                "act":%d,
                "param":{
                    "target":{"win":"%s","xid":"%s","nl":%d},
                    "sudo":[["%s"],null]
                },
                "tech":{}
            }
            """;

        return String.format(s, Action.SEARCH.value, win, xid, nl, val);
    }

    public static String goTo(String win, String xid, Integer nl) {
        String s =
            """
            {
                "act":%d,
                "fld":{"ist":null,"fmtKind":"ROUGH","notModified":false,"v":4},
                "param":{"target":{"win":"%s","xid":"%s","nl":%d}},
                "tech":{}
                }
            """;
        return String.format(s, Action.JUMP_TO.value, win, xid, nl);
    }

    public static String noRevise() {
        String s =
            """
            {
                "act":%d,
                "fld":{"ist":null,"fmtKind":"SHOW","notModified":false,"v":3},
                "param":{},
                "tech":{}
                }
            """;
        return String.format(s, Action.INPUT_TAB.value);
    }

    public static String warnOK() {
        String s =
            """
            {
                "act":%d,
                "fld":{"ist":null,"fmtKind":"SHOW","notModified":false,"v":5},
                "param":{},
                "tech":{}
                }
            """;
        return String.format(s, Action.INPUT_TAB.value);
    }

    public static String getFunction(String name) {
        return rptMap.get(name);
    }

    public static String getSalesOrderTransaction(String orderNO) {
        Pattern reg1 = Pattern.compile("^[A-Z](CC|DSR|REP|RCL)[\\d]{6,7}$");
        Pattern reg2 = Pattern.compile("^[A-Z](OL|LC)[\\d]{6}$");
        return reg1.matcher(orderNO).find() ? "2~1" : reg2.matcher(orderNO).find() ? "2~2" : "2~1";
    }

    public static String getInvoiceTransaction(String invoiceNO) {
        Pattern reg1 = Pattern.compile("[A-Z]FC.*");
        Pattern reg2 = Pattern.compile("[A-Z]PC.*");
        return reg1.matcher(invoiceNO).find() ? "2~2" : reg2.matcher(invoiceNO).find() ? "2~3" : "2~2";
    }

    public static String getPurchaseTransaction(String purchaseNO) {
        Pattern reg1 = Pattern.compile("[A-Z]CF.*");
        Pattern reg2 = Pattern.compile("[A-Z]CG.*");
        Pattern reg3 = Pattern.compile("[A-Z]CT.*");
        return reg1.matcher(purchaseNO).find()
            ? "2~1"
            : reg2.matcher(purchaseNO).find() ? "2~2" : reg3.matcher(purchaseNO).find() ? "2~3" : "2~1";
    }

    public static JSONObject rtnObj(Boolean success, MsgTyp msgTyp, String msg) {
        switch (msgTyp) {
            case INFO:
                //log.info(msg);
                break;
            case WARN:
                log.warn(msg);
                break;
            case ERROR:
                log.error(msg);
                break;
            default:
                break;
        }
        JSONObject rtn = new JSONObject();
        rtn.put("success", success);
        rtn.put("msgTyp", msgTyp);
        rtn.put("msg", msg);
        return rtn;
    }
}
