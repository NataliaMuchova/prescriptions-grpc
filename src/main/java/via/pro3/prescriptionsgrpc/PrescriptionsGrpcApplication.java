package via.pro3.prescriptionsgrpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import via.pro3.prescriptionsgrpc.service.PrescriptionServiceImpl;

import java.io.IOException;

@SpringBootApplication
public class PrescriptionsGrpcApplication {

  public static void main(String[] args) {
      SpringApplication.run(PrescriptionsGrpcApplication.class, args);
  }

}
