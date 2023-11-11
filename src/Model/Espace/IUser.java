/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model.Espace;

import java.util.Collection;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 * @param <T>
 */
public interface IUser<T> {
    public void sinscrire(Collection<T> personnes);
    public boolean sauthentifier(Scanner sc,Collection<T> personnes);
    public void consulterBatiments();
    public void consulterProduction();
}
