/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-06-06 15:47:35
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:04:54
 * @FilePath       : \server\src\main\java\sageassistant\model\TrackingNotes.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

@Getter
@Setter
public class TrackingNotes extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String TrackCode;
    private String Note;
    private String NoteBy;
    private Date NoteDate;
}
