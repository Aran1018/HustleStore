package com.triplebro.aran.hustlestore;

import com.triplebro.aran.hustlestore.manager.GoodsInfoManager;
import com.triplebro.aran.hustlestore.manager.UserInfoManager;
import com.triplebro.aran.hustlestore.utils.CheckLoginUtils;
import com.triplebro.aran.hustlestore.utils.GeneralUtils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() {
        System.out.println(GeneralUtils.GetMonthEN(8));
    }

    @Test
    public void BKTest() {
        System.out.println(GeneralUtils.GetMonthEN(8));
    }
}