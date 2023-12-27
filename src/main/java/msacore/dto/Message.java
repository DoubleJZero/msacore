package msacore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message<V> {

    private Headers headers;
    private V payload;

    @Data
    public static class Headers {
        private String id;
        private long timestamp;
        private String topic;
    }
}