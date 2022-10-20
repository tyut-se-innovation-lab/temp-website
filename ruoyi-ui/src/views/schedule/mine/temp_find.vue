<template>
    <div>
        <div class="input">
            <div class="net">
                <span>_RequestVerificationToken:</span>
                <div class="asp">
                    <el-input placeholder="请输入内容" v-model="mes.token">
                    </el-input>
                </div>
            </div>
            <div class="net">
                <span> ASP.NET_SessionId：</span>
                <div class="token">

                    <el-input placeholder="请输入内容" v-model="mes.asp">
                    </el-input>
                </div>
            </div>
            <div class="button">
                <el-button @click="isMessnull" type="primary">提交</el-button>
            </div>
        </div>
    </div>
</template>


<script>
import { tempfind } from "@/api/schedule/temp_find"
export default {
    data() {
        return {
            mes: {
                asp: "",
                token: ""
            }
        }
    },
    methods: {
        // 发送请求获取数据
        getmessage() {
            console.log(this.mes);
            tempfind(this.mes).then((res) => {
                this.$modal.msgSuccess(res.msg);
            })
        },
        // 判断是否为空
        isMessnull() {
            if (this.mes.asp == "" && this.mes.token == "") {
                this.$modal.msgError("内容不能为空！！！");
            }
            else {
                this.getmessage();

            }
        }
    }
}
</script>

<style scoped>
.input {
    margin-top: 40px;
}

.asp {
    display: inline-block;
    width: 600px;
    margin-left: 30px;
}

.token {

    display: inline-block;
    width: 600px;
    margin-left: 59px;
    box-sizing: border-box;
}

.net {
    padding-top: 20px;
    display: inline-block;
    width: 1100px;
}

.button {

    margin-top: 10px;
}
</style>
