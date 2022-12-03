package com.Salas.Automotores.controllers.dtos;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.Salas.Automotores.model.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDto {
	
	private Long id;
	private String name;
	
	@Autowired
	private static ModelMapper mapper = new ModelMapper();
	
	public static BrandDto mapToDto(Brand b) {
		BrandDto brandDto = mapper.map(b, BrandDto.class);
		return brandDto;
	}

	public static List<BrandDto> mapToDtoList(List<Brand> brandList) {
		List<BrandDto> brands = new ArrayList<>();
		for (Brand b : brandList) {
			brands.add(mapToDto(b));
		}
		return brands;
	}
}
