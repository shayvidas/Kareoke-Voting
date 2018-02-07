package com.shayvidas.eurovisionvoting.ui.singers_select;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Superman Is Awsome on 04/02/2018.
 */
public class Singers_Select_PresenterTest {

    @Test
    public void shouldReturnScreenCreatedTrue() throws Exception {

        Boolean output;

        Singers_Select_Interface singers_select_interface = null;

        Singers_Select_Presenter singers_select_presenter = new Singers_Select_Presenter(singers_select_interface);

        output = singers_select_presenter.checkForGame();

        assertThat(true, is (output));

    }



}