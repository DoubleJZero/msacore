package msacore.repository;

import msacore.dto.BatchDto;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;

/**
 * BatchRepository
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */
public class BatchRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public BatchRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int[] batchUpdate(BatchDto<?> dto){
        return namedParameterJdbcTemplate.batchUpdate(dto.getQuery(), SqlParameterSourceUtils.createBatch(dto.getData()));
    }
}
