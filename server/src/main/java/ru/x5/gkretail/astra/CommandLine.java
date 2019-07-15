package ru.x5.gkretail.astra;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import ru.x5.gkretail.tobacco.api.receipt.IReceiptTobaccoImporter;
import ru.x5.gkretail.tobacco.api.receipt.ReceiptTobaccoDto;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandLine {

  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private IReceiptTobaccoImporter tobaccoImporter;

  private static final Log logger = LogFactory.getLog(CommandLine.class);

  public void run() {
    logger.trace("run command line");
    try {
      boolean exit = false;
      do {
        System.out.println("Введите команду:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final String comamnd = reader.readLine();
        switch (comamnd) {
          case "1": {
            tobaccoImporter.postProcessXml(new ReceiptTobaccoDto());
            break;
          }
          case "0": {
            exit = true;
            break;
          }
          default: {
            output("Unknown command");
          }
        }
      } while (!exit);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void output(String str) {
    System.out.println("Output " + str);
  }
}
