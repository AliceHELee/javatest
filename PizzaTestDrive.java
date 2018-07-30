public class PizzaTestDrive {
    public static void main(String[] args) {
        PizzaStore anyangStore = new AYPizzaStore();
        PizzaStore ansanStore  = new ASPizzaStore();

        Pizza pizza = anyangStore.orderPizza("cheese");
        System.out.println("Crystal ordered a " + pizza.getName() + "\n");

        Pizza pizza = ansanStore.orderPizza("cheese");
        System.out.println("Alice ordered a " + pizza.getName() + "\n");        
    }
}