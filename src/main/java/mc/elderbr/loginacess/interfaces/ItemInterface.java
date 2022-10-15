package mc.elderbr.loginacess.interfaces;

import mc.elderbr.loginacess.model.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ItemInterface extends Global {

    List<String> ITEM_LISTA = new ArrayList<>();
    Map<String, Item> ITEM_MAP = new HashMap<>();

}
