<div class="container">
    <el-row class="bg-color">
        <el-col :span="20"
                :offset="4">
            <table class="table table-bordered value-center">
                <tr class="value-center" style="background-color:#f5f7fa">
                    <th class="headerRow value-center" width=20%>事件类型</th>
                    <th class="headerRow value-center" width=30%>${category}</th>
                    <th class="headerRow value-center" width=20%>等级</th>
                    <th class="headerRow value-center" width=30%>${level}</th>
                </tr>
                <tr class="value-center" style="background-color:#f5f7fa">
                    <th class="headerRow value-center" width=20%>事件概述</th>
                    <th class="headerRow value-center" colspan="3" width=50%>${remark}</th>
                </tr>
            </table>
        </el-col>
    </el-row>
</div>