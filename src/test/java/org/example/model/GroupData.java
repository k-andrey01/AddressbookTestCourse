package org.example.model;

import java.util.Objects;

public class GroupData {

    private final String id;
    private final String groupName;
    private final String header;
    private final String footer;

    public GroupData(String groupName, String header, String footer) {
        this.id = null;
        this.groupName = groupName;
        this.header = header;
        this.footer = footer;
    }


    public GroupData(String id, String groupName, String header, String footer) {
        this.id = id;
        this.groupName = groupName;
        this.header = header;
        this.footer = footer;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "id='" + id + '\'' +
                ", groupName='" + groupName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupData groupData = (GroupData) o;
        return Objects.equals(id, groupData.id) && Objects.equals(groupName, groupData.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groupName);
    }
}
