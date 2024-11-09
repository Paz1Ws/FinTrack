import Clases.ControladorSistema;
import Clases.TransaccionManager;
import SistemaInterfaz.*;

public class Sistema {
        public static void main(String[] args) {
                TransaccionManager transaccionManager = new TransaccionManager();
                VistaSistema view = new VistaSistema();
                new ControladorSistema(transaccionManager, view);
        }
}