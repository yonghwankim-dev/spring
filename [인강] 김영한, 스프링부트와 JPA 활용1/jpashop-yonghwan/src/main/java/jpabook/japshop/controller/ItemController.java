package jpabook.japshop.controller;

import jpabook.japshop.domain.Book;
import jpabook.japshop.domain.Item;
import jpabook.japshop.service.ItemService;
import jpabook.japshop.service.UpdateItemDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    /**
     * 상품 등록 폼
     */
    @GetMapping("/items/new")
    public String createForm(Model model){
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    /**
     * 상품 등록
     */
    @PostMapping("/items/new")
    public String create(BookForm form){
        Book book = form.createBook();

        itemService.saveItem(book);

        return "redirect:/";
    }

    /**
     * 상품 목록
     */
    @GetMapping("/items")
    public String list(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    /**
     * 상품 수정 폼
     */
    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model){
        Book item = (Book) itemService.findOne(itemId);
        BookForm form = BookForm.createBookForm(item);
        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    /**
     * 상품 수정
     */
    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@PathVariable("itemId") Long itemId, BookForm form){
        UpdateItemDto updateItemDto = UpdateItemDto.builder()
                                                   .id(itemId)
                                                   .name(form.getName())
                                                   .price(form.getPrice())
                                                   .stockQuantity(form.getStockQuantity())
                                                   .build();

        itemService.updateItem(updateItemDto);
        return "redirect:/items";
    }
}
