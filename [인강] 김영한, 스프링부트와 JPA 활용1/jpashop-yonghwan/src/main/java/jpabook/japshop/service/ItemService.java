package jpabook.japshop.service;

import jpabook.japshop.domain.Book;
import jpabook.japshop.domain.Item;
import jpabook.japshop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public Long saveItem(Item item){
        itemRepository.save(item);
        return item.getId();
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }


    // 변경 감지 방법
    @Transactional
    public void updateItem(UpdateItemDto updateItemDto){
        Item findItem = itemRepository.findOne(updateItemDto.getId());
        findItem.changeInfo(updateItemDto);
    }
}
