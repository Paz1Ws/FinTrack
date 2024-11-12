package Main;

import Clases.ControladorSistema;
import Clases.TransaccionManager;
import Interfaz.*;

public class Sistema {
        public static void main(String[] args) {
                TransaccionManager transaccionManager = new TransaccionManager();
                VistaSistema view = new VistaSistema();
                Login login = new Login();
                new ControladorSistema(transaccionManager, view, login);
        }
}