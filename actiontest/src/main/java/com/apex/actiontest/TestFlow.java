package com.apex.actiontest;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TestFlow
 * @Description: java类作用描述
 * @Author: chentao
 * @CreateDate: 2025/5/21
 * @UpdateUser: updater
 * @UpdateDate: 2025/5/21
 * @UpdateRemark: 更新内容
 * @Version: 1.0
 */
public class TestFlow implements Parcelable {
    private final int id;
    private final String name;
    private final String description;



    private String resolvedAction;

    private String action;
    private boolean isChecked;
    private Status status;
    private String testSteps;
    private int timeOut;
    private String param;
    private int level;
    private List<TestFlow> subFlows;

    public enum Status {
        HIDE, PENDING, RUNNING, SUCCESS, FAILED
    }

    public TestFlow(int id, String name, String description, String action, boolean isChecked,
                    Status status, String testSteps, int timeOut, String param, int level) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.action = action;
        this.isChecked = isChecked;
        this.status = status;
        this.testSteps = testSteps;
        this.timeOut = timeOut;
        this.param = param;
        this.level = level;
        this.subFlows = new ArrayList<>();
    }

    protected TestFlow(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        action = in.readString();
        isChecked = in.readByte() != 0;
        int tmpStatus = in.readInt();
        status = tmpStatus == -1 ? null : Status.values()[tmpStatus];
        testSteps = in.readString();
        timeOut = in.readInt();
        param = in.readString();
        level = in.readInt();
        subFlows = new ArrayList<>();
        in.readTypedList(subFlows, TestFlow.CREATOR);
    }

    public static final Creator<TestFlow> CREATOR = new Creator<TestFlow>() {
        @Override
        public TestFlow createFromParcel(Parcel in) {
            return new TestFlow(in);
        }

        @Override
        public TestFlow[] newArray(int size) {
            return new TestFlow[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTestSteps() {
        return testSteps;
    }

    public void setTestSteps(String testSteps) {
        this.testSteps = testSteps;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<TestFlow> getSubFlows() {
        return subFlows;
    }

    public void setSubFlows(List<TestFlow> subFlows) {
        this.subFlows = subFlows;
    }

    // 新增 addSubFlow 方法
    public void addSubFlow(TestFlow subFlow) {
        subFlows.add(subFlow);
    }

    public String getResolvedAction() {
        return resolvedAction;
    }

    public void setResolvedAction(String resolvedAction) {
        this.resolvedAction = resolvedAction;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(action);
        dest.writeByte((byte) (isChecked ? 1 : 0));
        dest.writeInt(status == null ? -1 : status.ordinal());
        dest.writeString(testSteps);
        dest.writeInt(timeOut);
        dest.writeString(param);
        dest.writeInt(level);
        dest.writeTypedList(subFlows);
    }

    @Override
    public String toString() {
        return "TestFlow{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", action='" + action + '\'' +
                ", isChecked=" + isChecked +
                ", status=" + status +
                ", testSteps='" + testSteps + '\'' +
                ", timeOut=" + timeOut +
                ", param=" + param +
                ", level=" + level +
                ", subFlows=" + subFlows +
                '}';
    }
}