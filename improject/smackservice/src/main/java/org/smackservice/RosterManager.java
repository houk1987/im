package org.smackservice;

import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by HK on 2014/10/6.
 */
public class RosterManager {

    public static List<RosterEntry> getRosters() {
        List<RosterEntry> EntriesList = new ArrayList<>();
        Roster roster = SmackConnection.getInstance().getRoster();
        Collection<RosterEntry> rosterEntry = roster.getEntries();
        Iterator<RosterEntry> i = rosterEntry.iterator();
        while (i.hasNext())
            EntriesList.add(i.next());
        return EntriesList;
    }

    public static RosterEntry getRosterEntry(String jid) {
        if (jid == null) return null;
        SmackConnection.getInstance().getRoster().reload();
        return SmackConnection.getInstance().getRoster().getEntry(jid);
    }
}

