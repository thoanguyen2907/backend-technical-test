package shopify.demo.shared;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PageList<T> {
    private int offset;
    private int limit;
    private long totalRecords;
    private int totalPage;
    private List<T> records;
}
