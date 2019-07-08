package io.github.dndanoff.entry_point.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListInputDto implements Dto{
	
    public static final String ORDER_ASC = "ASC";
    public static final String ORDER_DESC = "DESC";
    
    public static final String DEFAULT_SORTBY = "id";
    public static final String DEFAULT_ORDER = ORDER_ASC;
    public static final Integer DEFAULT_LIMIT = 200;
    public static final Integer DEFAULT_OFFSET = 0;
    
    @NotBlank
	private String sortBy = DEFAULT_SORTBY;
    @NotBlank
	private String order = DEFAULT_ORDER;
    @NotNull
	private Integer limit = DEFAULT_LIMIT;
    @NotNull
	private Integer offset = DEFAULT_OFFSET;
}
