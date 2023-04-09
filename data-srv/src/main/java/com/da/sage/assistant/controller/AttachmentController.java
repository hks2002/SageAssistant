/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-26 22:53:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-04-08 21:46:22                                                                      *
 * @FilePath              : src/main/java/com/da/sage/assistant/controller/AttachmentController.java                 *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.controller;

import com.da.sage.assistant.service.AttachmentService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/Data/FileUpload")
    public String handleFileUpload(
        @RequestParam(value = "Pn", required = true) String pn,
        @RequestParam(value = "Cat", required = true) String cat,
        @RequestParam("file") final MultipartFile uploadFile,
        HttpServletResponse response
    ) {
        return attachmentService.handleFileUpload(pn, cat, uploadFile, response);
    }

    @GetMapping("/Data/FileDelete")
    public String handleFileDelete(
        @RequestParam(value = "Path", required = true) String file,
        HttpServletResponse response
    ) {
        return attachmentService.handleFileDelete(file, response);
    }

    /*
     * @param pn could be Pn or PnRoot, PnRoot without version
     */
    @GetMapping("/Data/AttachmentPath")
    public String getAttachmentPath(@RequestParam(value = "Pn", required = false, defaultValue = "NULL") String pn) {
        return attachmentService.getAttachmentPath(pn);
    }
}
