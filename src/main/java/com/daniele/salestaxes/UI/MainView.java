package com.daniele.salestaxes.UI;

import com.daniele.salestaxes.domain.Cart;
import com.daniele.salestaxes.domain.goods.Item;
import com.daniele.salestaxes.services.ItemCreatorService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Route
public class MainView extends VerticalLayout {

    @Autowired
    Cart shoppingCart;
    private Grid<Item> itemListGrid = new Grid<>();
    private Grid<Item> cartGrid = new Grid<>();
    private Map<String, Item> cartMap = new LinkedHashMap<>();
    private ItemCreatorService itemCreatorService = new ItemCreatorService();

    public MainView() {
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSizeFull();
        H1 header = new H1("Sale Taxes Problem");
        H3 headerCart = new H3("Cart:");
        Button checkout = createCheckoutButtun();
        createItemListGrid();
        createCartGrid();
        add(header,  itemListGrid, headerCart, cartGrid, checkout);
    }

    private void createCartGrid(){
        cartGrid.setItems(cartMap.values());
        cartGrid.addColumn(Item::getName).setHeader("Name");
        cartGrid.addColumn(Item::getDescription).setHeader("Description");
        cartGrid.addColumn(new ComponentRenderer<>(item -> {
            return getImportedIcon(item);
        })).setHeader("Imported/Not Imported");
        cartGrid.addColumn(Item::getQuantity).setHeader("Quantity");
        cartGrid.addColumn(Item::getPrice).setHeader("Price");

    }
    private void createItemListGrid() {
        itemListGrid.setItems(itemCreatorService.getItemList());
        itemListGrid.addColumn(Item::getName).setHeader("Name");
        itemListGrid.addColumn(Item::getDescription).setHeader("Description");

        // adding imported-icon
        itemListGrid.addColumn(new ComponentRenderer<>(item -> {
            return getImportedIcon(item);
        })).setHeader("Imported/Not Imported");

        itemListGrid.addColumn(Item::getPrice).setHeader("Price");
        itemListGrid.addComponentColumn(item -> createBuyButton(itemListGrid, item));
    }

    private Icon getImportedIcon(Item item) {
        if (item.getIsImported()) {
            return new Icon(VaadinIcon.TRUCK);
        } else {
            return new Icon(VaadinIcon.HOME);
        }
    }

    public Button createCheckoutButtun(){
        Button button = new Button("Checkout", buttonClickEvent -> {
            Dialog receiptDialog = new Dialog();
            ListDataProvider<Item> listDataProvider = (ListDataProvider<Item>) cartGrid.getDataProvider();
            listDataProvider.getItems().stream().forEach(item -> shoppingCart.addItem(item));

            TextArea area = new TextArea("Receipt");
            area.setValue(shoppingCart.checkOut().toString());
            area.setReadOnly(true);
            area.setWidth("1000");
            receiptDialog.add(area);
            receiptDialog.open();

            //reset cartGrid
            cartMap = new HashMap<>();
            cartGrid.setItems(cartMap.values());
            //reset shoppingCart
            shoppingCart.discardAll();

            ListDataProvider<Item> itemListDataProvider = (ListDataProvider<Item>) itemListGrid.getDataProvider();
            itemListDataProvider.getItems().stream().forEach(item -> item.setQuantity(0));
        });
        return button;
    }

    private Button createBuyButton(Grid<Item> grid, Item item) {
        Button button = new Button("Add to Cart", clickEvent -> {
            ListDataProvider<Item> dataProvider = (ListDataProvider<Item>)grid.getDataProvider();

            if (cartMap.containsKey(item.getId())){
                 Item temp = cartMap.get(item.getId());
                 temp.setQuantity(temp.getQuantity() + 1);
            }
            else {
               item.setQuantity(item.getQuantity() + 1);
               cartMap.put(item.getId(), item);
            }

            itemListGrid.getDataProvider().refreshAll();
            cartGrid.setItems(cartMap.values());

        });
        return button;
    }
}
