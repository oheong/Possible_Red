<template>
  <div class="user" id="findpwd">
    <div class="wrapC">
      <!-- header -->
      <div class="wrap components-page p-1">
        <HeaderComponent headerTitle="Find Password" :isBack="true" />
      </div>

      <!-- email input -->
      <div class="input-with-label jua">
        <input
          v-model="email"
          v-bind:class="{error : error.email, complete:!error.email&&email.length!==0}"
          @keyup.enter="Login"
          id="email"
          placeholder="이메일을 입력하세요."
          type="text"
        />
        <label for="email" class="jua">이메일</label>
        <div class="error-text" v-if="error.email">{{error.email}}</div>
      </div>
      <div v-if="!isEmailOK">
        <!-- 이메일 인증 X -->
        <button @click="sendMail" class="sign">인증번호 받기</button>

        <div class="input-with-label jua">
          <input v-model="randNum" id="randNum" placeholder="인증번호를 입력하세요" type="text" />
          <label for="randNum" class="jua">인증번호</label>
        </div>
        <br />
        <button @click="isSameNum" class="sign">인증하기</button>
      </div>

      <div v-else>
        <!-- 이메일 인증이 확인되면 버튼 생성 -->
        <div class="wrap components-page">
          <LargeButton
            text="비밀번호 변경하기"
            :isBackground="isSubmit"
            @click.native="onSubmit"
            :disabled="!isSubmit"
            :class="{disabled : !isSubmit}"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "../../components/css/user.scss";
import * as EmailValidator from "email-validator";
import HeaderComponent from "../../components/common/Header.vue";
import LargeButton from "../../components/common/ButtonLarge";
import UserApi from "../../api/UserApi";
import EmailApi from "../../api/EmailApi";

export default {
  name: "FindPwd",

  components: {
    HeaderComponent,
    LargeButton,
  },

  data() {
    return {
      email: "",
      error: {
        email: false,
      },
      isSubmit: false,
      isEmailOK: false, // 메일인증이 됐는지 안됐는지
      randNum: "", // 내가 입력하는 인증번호
      num: "", // 받아오는 인증번호
    };
  },

  watch: {
    email: function (v) {
      this.checkForm();
    },
  },

  methods: {
    sendMail() {
      EmailApi.requestRandomNumber(
        this.email,
        (res) => {
          this.num = res.data;
          alert("인증번호가 발송되었습니다.\n메일을 확인해주세요");
        },
        (error) => {
          alert("메일을 정확히 입력해주세요");
        }
      );
    },
    isSameNum() {
      if (this.randNum == this.num) {
        alert("이메일 인증이 완료되었습니다");
        this.isEmailOK = true;
      } else {
        alert("인증번호를 정확하게 입력해주세요");
      }
    },
    checkForm() {
      // email 유효성 검사
      if (this.email.length >= 0 && !EmailValidator.validate(this.email))
        this.error.email = "이메일 형식이 아닙니다.";
      else this.error.email = false;

      let isSubmit = true;
      Object.values(this.error).map((v) => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },

    onSubmit() {
      UserApi.requestEmailCheck(
        this.email,
        (res) => {
          // 입력된 이메일이 존재하지 않을 때
          if (res.data == 0) {
            this.isSubmit = false;
            this.error.email = "일치하는 이메일이 없습니다.";
            // 피드백 필요
          } else {
            // 입력된 이메일 존재할 때
            // 반환받은 멤버정보를 전달
            this.$router.push({
              name: "ChangePwd",
              params: { member: res.data },
            });
          }
        },
        (error) => {}
      );
    },
  },
};
</script>

<style>
.jua {
  font-family: "Jua", sans-serif;
}
.sign {
  font-family: "Jua", sans-serif;
  margin-left: 2%;
  color: navy;
}
</style>