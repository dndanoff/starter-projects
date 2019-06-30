package io.github.dndanoff.entry_point.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListInputDto implements Dto{
    public static final String ORDER_ASC = "ASC";
    public static final String ORDER_DESC = "DESC";
    
    public static final String DEFAULT_SORTBY = null;
    public static final String DEFAULT_ORDER = ORDER_ASC;
    public static final Integer DEFAULT_LIMIT = 200;
    public static final Integer DEFAULT_OFFSET = 0;
	
    @Builder.Default
	protected String sortBy = DEFAULT_SORTBY;
    @Builder.Default
	protected String order = DEFAULT_ORDER;
    @Builder.Default
	protected Integer limit = DEFAULT_LIMIT;
    @Builder.Default
	protected Integer offset = DEFAULT_OFFSET;
}
