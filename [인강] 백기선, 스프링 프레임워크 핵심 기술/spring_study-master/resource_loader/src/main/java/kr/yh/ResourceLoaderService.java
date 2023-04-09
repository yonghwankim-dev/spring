package kr.yh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ResourceLoaderService {
    private final ResourceLoader resourceLoader;

    @Autowired
    public ResourceLoaderService(@Qualifier("classPathXmlApplicationContext") ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public ResourceLoader getResourceLoader(){
        return resourceLoader;
    }

    public Resource getResource(String path){
        return this.getResourceLoader().getResource(path);
    }
}
