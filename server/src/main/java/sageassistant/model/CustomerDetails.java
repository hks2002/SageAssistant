/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-31 16:16:15
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 13:59:31
 * @FilePath       : \server\src\main\java\sageassistant\model\CustomerDetails.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

@Getter
@Setter
public class CustomerDetails extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String CustomerCode;
    private String CustomerName0;
    private String CustomerName1;
    private String Address0;
    private String Address1;
    private String PostCode;
    private String Country;
    private String State;
    private String City;
    private String Tel0;
    private String Tel1;
    private String Tel2;
    private String Tel3;
    private String Tel4;
    private String Fax0;
    private String Mobile0;
    private String Email0;
    private String Email1;
    private String Email2;
    private String Email3;
    private String Email4;
    private String Website;
}
