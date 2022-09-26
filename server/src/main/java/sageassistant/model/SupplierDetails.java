/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:01:29
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:03:43
 * @FilePath       : \server\src\main\java\sageassistant\model\SupplierDetails.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

@Getter
@Setter
public class SupplierDetails extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String SupplierCode;
    private String SupplierName0;
    private String SupplierName1;
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
