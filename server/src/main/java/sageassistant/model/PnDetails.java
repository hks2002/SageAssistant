/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:01:29
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:00:36
 * @FilePath       : \server\src\main\java\sageassistant\model\PnDetails.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

@Getter
@Setter
public class PnDetails extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private Integer ROWID;
    private String PNROOT;
    private String PN;
    private String Cat;
    private String Version;
    private String Comment;
    private String Desc1;
    private String Desc2;
    private String Desc3;
    private Integer Status;
    private Date CreateDate;
}
