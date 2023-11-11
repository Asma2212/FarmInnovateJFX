package Model.Inventaire;

 interface InputParser<T> {
        T parse(String input) throws Exception;
    }
