package com.social.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilteringVariable {
	private String[] brand;
	private String[] category;
	private String[] color;
	private long discount;
	private String[] gender;
	private long maxPrice;
	private long minPrice;
	private String order;
	private String searchedItem;
}
