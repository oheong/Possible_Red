<template>
  <div class="inputBox shadow jua">
      <input type="text" v-model="comment.commentContent" placeholder="Tell me your thinking" @keyup.enter="addComment">
      <span class="addContainer" @click="addComment">
          <i class="addBtn fas fa-plus" aria-hidden="true"></i>
      </span>

      <modal v-if="showModal" @close="showModal = false">
          <h3 slot="header" class="jua">경고</h3>
          <h5 slot="body" class="jua">댓글을 입력하세요!!!!! </h5>
          <span slot="footer" @click="showModal = false">
              <i class="closeModalBtn fas fa-times" aria-hidden="true"></i>
          </span> 
      </modal> 
  </div>
</template>

<script>
import Modal from '../../components/common/Modal.vue';
import CommentApi from '../../api/CommentApi.js';

export default {
    props:{
      postNo:Number
    },
    data(){
      return{
        newComment:'',
        showModal:false,
        comment:{
            commentContent: "",
            memberNo: 0, //댓글작성자
            postNo: 0, 
        },
      }
    },
    methods:{
      addComment(){
        if(this.comment.commentContent!=""){ //인풋 박스의 입력 값이 있을 때만 저장
          // console.log("after"+this.comment.commentContent);
          var value=this.comment.commentContent && this.comment.commentContent.trim(); //인풋 박스에 입력된 텍스트의 앞뒤 공백 문자열 제거
          this.$emit('addComment', value);
          this.clearInput(); //인풋 박스의 입력 값을 초기화

        }else{
          this.showModal=!this.showModal;
        }
        // console.log(this.comment)
        // console.log("before"+this.comment.commentContent);
          
      },
      clearInput(){
        this.comment.commentContent=''
      }
    },
    components:{
      Modal:Modal
    },
    created(){
      this.comment.memberNo=this.$session.get('user').memberNo
      this.comment.postNo=this.$session.get('user').postNo
    },
}
</script>

<style scoped>
    input:focus{
        outline:none;
    }
    .inputBox{
        background:white;
        height:50px;
        line-height:50px;
        border-radius:5px;
    }
    .inputBox input{
        border-style:none;
        font-size:0.9rem;
    }
    .addContainer{
        float:right;
        background:linear-gradient(to right, #6478FB, #8763FB);
        display:block;
        width:3rem;
        border-radius: 0 5px 5px 0;
        text-align:center;
    }
    .addBtn{
        color:white;
        vertical-align:middle;
    }
    .jua{
    font-family: 'Jua', sans-serif;
    font-size:x-large;
    }
</style>