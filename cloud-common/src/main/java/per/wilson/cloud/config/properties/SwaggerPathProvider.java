package per.wilson.cloud.config.properties;

import org.apache.commons.lang3.StringUtils;
import springfox.documentation.spring.web.paths.AbstractPathProvider;

import javax.servlet.ServletContext;


/**
 * SwaggerPathProvider-配置文档导出BasePath
 *
 * @author Wilson
 * @date 2018/10/3
 */
public class SwaggerPathProvider extends AbstractPathProvider {

    private final String CONTEXT_PATH;

    public SwaggerPathProvider(String contextPath) {
        super();
        this.CONTEXT_PATH = contextPath;
    }

    public SwaggerPathProvider(ServletContext servletContext) {
        super();
        this.CONTEXT_PATH = StringUtils.isEmpty(servletContext.getContextPath()) ? "/"
                : servletContext.getContextPath();
    }

    @Override
    protected String applicationPath() {
        return CONTEXT_PATH;
    }

    @Override
    protected String getDocumentationPath() {
        return CONTEXT_PATH;
    }

}
