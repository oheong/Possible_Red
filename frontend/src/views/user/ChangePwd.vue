<template>
  <div class="user" id="changePwd">
    <div class="wrapC">
      <!-- header -->
      <div class="wrap components-page p-1">
        <HeaderComponent headerTitle="Change Password" :isBack="true" />
      </div>

      <!-- new password -->
      <div class="input-with-label jua">
        <input
          v-model="password"
          type="password"
          v-bind:class="{error : error.password, complete:!error.password&&password.length!==0}"
          id="password"
          placeholder="비밀번호를 입력하세요."
        />
        <label for="password" class="jua">비밀번호</label>
        <div class="error-text" v-if="error.password">{{error.password}}</div>
      </div>

      <!-- check password -->
      <div class="input-with-label jua">
        <input
          v-model="passwordChk"
          type="password"
          v-bind:class="{error : error.passwordChk, complete:!error.passwordChk&&passwordChk.length!==0}"
          id="passwordChk"
          placeholder="비밀번호를 한번 더 입력하세요."
        />
        <label for="password" class="jua">비밀번호 확인</label>
        <div class="error-text" v-if="error.passwordChk">{{error.passwordChk}}</div>
      </div>

      <!--  -->
      <div class="wrap components-page">
        <LargeButton
          text="확인"
          :isBackground="isSubmit"
          @click.native="pwdchanged"
          :disabled="!isSubmit"
          :class="{disabled : !isSubmit}"
        />
      </div>
    </div>
  </div>
</template>

<script>
import "../../components/css/user.scss";
import * as EmailValidator from "email-validator";
import HeaderComponent from "../../components/common/Header.vue";
import PV from "password-validator";
import LargeButton from "../../components/common/ButtonLarge";
import UserApi from "../../api/UserApi";

export default {
  name: "ChangePwd",
  components: {
    HeaderComponent,
    LargeButton,
  },
  created() {
    this.component = this;
    this.member = this.$route.params.member;
    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(100)
      .has()
      .digits()
      .has()
      .letters();
  },

  watch: {
    password: function (v) {
      this.checkForm();
    },
    passwordChk: function (v) {
      this.checkForm();
    },
  },

  methods: {
    checkForm() {
      // password 유효성 검사
      if (
        this.password.length >= 0 &&
        !this.passwordSchema.validate(this.password)
      )
        this.error.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
      else this.error.password = false;

      // password 일치 검사
      if (this.passwordChk.length >= 0 && this.passwordChk != this.password)
        this.error.passwordChk = "비밀번호가 일치하지 않습니다.";
      else this.error.passwordChk = false;

      // 위의 두 조건 모두 만족시 버튼 활성화
      let isSubmit = true;
      Object.values(this.error).map((v) => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },

    pwdchanged() {
      this.member.pwd = this.password;
      // password 변경 요청
      UserApi.requestUpdatePassword(
        this.member,
        (res) => {
          alert("비밀번호가 변경되었습니다! 로그인을 다시 해 주세요!");
          this.$router.push("/");
        },
        (error) => {}
      );
    },
  },

  data() {
    return {
      password: "",
      passwordChk: "",
      member: Object,
      passwordSchema: new PV(),
      error: {
        passowrd: false,
        passwordChk: false,
      },
      isSubmit: false,
      component: this,
    };
  },
};
</script>

<style>
.jua {
  font-family: "Jua", sans-serif;
}
.btn-default.noneBackground.disabled {
  font-family: "Jua", sans-serif;
  color: white;
  background: linear-gradient(to right, #f48fb1, #3949ab);
  border-color: white;
  border-radius: 5px;
}
.components-page .btn-default {
  font-family: "Jua", sans-serif;
  color: white;
  border-color: white;
  border-radius: 5px;
  background: linear-gradient(to left, #f48fb1, #3949ab);
}
</style>
