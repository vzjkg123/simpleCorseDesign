package top.ltcnb.shoppingsystembackend.DO;


import lombok.Data;
import top.ltcnb.shoppingsystembackend.property.Status;

@Data
public class R<T> {
    final private Integer status;
    final private String info;
    final private T data;

    private R(Integer status, String info, T data) {
        this.status = status;
        this.info = info;
        this.data = data;
    }


    public static <T> R<T> success(T data) {
        return new R<T>(Status.SUCCESS, null, data);
    }

    public static <T> R<T> success(String info) {
        return new R<T>(Status.SUCCESS, info, null);
    }

    public static <T> R<T> failed(String info, T data) {
        return new R<T>(Status.ERROR, null, data);
    }

    public static <T> R<T> failed(String info) {
        return new R<T>(Status.ERROR, info, null);
    }


}
