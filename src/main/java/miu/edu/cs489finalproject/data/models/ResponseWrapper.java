package miu.edu.cs489finalproject.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseWrapper<T> {
    private String status;
    private String message;
    private T response;
}
