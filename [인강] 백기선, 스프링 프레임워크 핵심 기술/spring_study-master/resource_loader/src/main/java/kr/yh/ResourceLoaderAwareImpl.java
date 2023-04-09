package kr.yh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class ResourceLoaderAwareImpl implements ResourceLoaderAware {
    private ResourceLoader resourceLoader;

    public ResourceLoader getResourceLoader(){
        return resourceLoader;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Resource getResource(String path){
        return this.getResourceLoader().getResource(path);
    }

}
