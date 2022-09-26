/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:01:29
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 13:58:47
 * @FilePath       : \server\src\main\java\sageassistant\model\Attachment.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

@Getter
@Setter
public class Attachment extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String PN;
    private String DocType;
    private String Path;
    private String File;
    private String Cat;
}
