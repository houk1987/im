package com.comunication.chat;

import com.comunication.connection.ConnectionManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GroupChatManagerTest {

    @BeforeClass
    public static void setUp() throws Exception {
        ConnectionManager.createConnection();
        boolean isLoginSuccess = ConnectionManager.login("10000", "123");

    }

    @Test
    public void testCreateGroupChat() throws Exception {
        List<String> memberList = new ArrayList<>();
        memberList.add("10221");
        GroupChatManager.createGroupChat("1222",memberList);
    }

    @Test
    public void testModifyGroupChatInfo() throws Exception {

    }

    @Test
    public void testDeleteGroupChatByGroupName() throws Exception {

    }

    @Test
    public void testDeleteGroupChat() throws Exception {

    }

    @Test
    public void testJoinGroupChatByGroupName() throws Exception {

    }

    @Test
    public void testJoinGroupChatByGroupId() throws Exception {

    }

    @Test
    public void testGetAllGroupChat() throws Exception {

    }

    @Test
    public void testGetMemberIdList() throws Exception {

    }

    @Test
    public void testCreateGroupChatId() throws Exception {

    }
}