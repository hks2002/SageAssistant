/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:01:29
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:00:44
 * @FilePath       : \server\src\main\java\sageassistant\model\PnStatus.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

@Getter
@Setter
public class PnStatus extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String PN;
    private String Desc1;
    private String Desc2;
    private String Desc3;
    private String PNStatus;
    private String WC;
    private String ProjectNO;
    private String CustomerCode;
    private String CustomerName;
}
