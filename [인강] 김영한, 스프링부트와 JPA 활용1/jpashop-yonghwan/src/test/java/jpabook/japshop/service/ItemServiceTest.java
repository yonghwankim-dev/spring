package jpabook.japshop.service;

import jpabook.japshop.domain.Item;
import jpabook.japshop.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {
    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void 상품등록() throws Exception{
        //given
        Item item = Item.builder()
                        .name("jpa")
                        .build();

        //when
        Long savedId = itemService.saveItem(item);

        //then
        assertThat(item).isEqualTo(itemRepository.findOne(savedId));
    }
}