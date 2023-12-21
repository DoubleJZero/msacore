package msacore.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * BatchDto
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */
public class BatchDto<D> {
    @Getter
    String query;

    @Getter
    @Setter
    List<D> data;
}
