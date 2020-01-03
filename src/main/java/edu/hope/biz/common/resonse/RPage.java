package edu.hope.biz.common.resonse;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.hope.biz.common.constants.RStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author fanwenhao
 * @date 2019/9/28 10:26
 */
@Data
public class RPage<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String msg = RStatus.SUCCESS.getMessage();
    
    private int code = RStatus.SUCCESS.getValue();

    private Integer total;

    private Integer size;

    private Integer current;

    private List<T> data;

    private Integer pages;
    
    public RPage(IPage<T> page) {
        if (page == null) {
            return;
        }
        this.total = (int)page.getTotal();
        this.current = (int)page.getCurrent();
        this.size = (int)page.getSize();
        this.data = page.getRecords();

        if(this.total == 0){
            this.pages = 0;
        }else{
            pages = total/size;
            if(total%size != 0){
                ++pages;
            }
        }
    }

    public RPage<T> msg(String msg) {
        this.msg = msg;
        return this;
    }
    
    public RPage<T> code(int code) {
        this.code = code;
        return this;
    }

}
