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
      try{
          Server server = ServerBuilder.forPort(3004)
              .addService(new PrescriptionServiceImpl())
              .build();
          server.start();
          System.out.println("Server started, listening on " + server.getPort());
          server.awaitTermination();
      }catch(IOException | InterruptedException ioe){
          System.out.println("Caught exception: " + ioe);
      }
  }

}
