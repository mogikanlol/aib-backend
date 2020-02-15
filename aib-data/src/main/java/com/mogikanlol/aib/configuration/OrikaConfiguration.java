package com.mogikanlol.aib.configuration;

import com.mogikanlol.aib.domain.Thread;
import com.mogikanlol.aib.dto.ThreadDto;
import com.mogikanlol.aib.dto.ThreadWithPostsDto;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrikaConfiguration implements OrikaMapperFactoryConfigurer {
    @Override
    public void configure(MapperFactory orikaMapperFactory) {
        orikaMapperFactory.classMap(Thread.class, ThreadDto.class)
                .customize(new CustomMapper<Thread, ThreadDto>() {
                    @Override
                    public void mapAtoB(Thread thread, ThreadDto threadDto, MappingContext context) {
                        if (thread.getImageName() != null) {
                            threadDto.setImageUrl("http://localhost:8081/images/" + thread.getImageName());
                        }
                    }
                })
                .byDefault()
                .register();

        orikaMapperFactory.classMap(Thread.class, ThreadWithPostsDto.class)
                .customize(new CustomMapper<Thread, ThreadWithPostsDto>() {
                    @Override
                    public void mapAtoB(Thread thread, ThreadWithPostsDto threadDto, MappingContext context) {
                        if (thread.getImageName() != null) {
                            threadDto.setImageUrl("http://localhost:8081/images/" + thread.getImageName());
                        }
                    }
                })
                .byDefault()
                .register();
    }
}
