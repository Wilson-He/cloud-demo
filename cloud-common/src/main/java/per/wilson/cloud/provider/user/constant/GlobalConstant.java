package per.wilson.cloud.provider.user.constant;

/**
 * GlobalConstant
 *
 * @author Wilson
 * @date 18-7-10
 */
public interface GlobalConstant {
    String BASE_PACKAGE = "per.wilson.cloud";
    String MODEL_PACKAGE = BASE_PACKAGE + ".entity.model";
    String DAO_PACKAGE = BASE_PACKAGE + ".dao";
    String SERVICE_PACKAGE = BASE_PACKAGE + ".service";
    String MAPPER_PATH = "classpath*:mappers/*.xml";
}
