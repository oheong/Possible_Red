<template>
  <div class="wrap">
    <b-navbar toggleable="lg" type="dark" variant="info">
        <b-navbar-nav>
            <b-nav-item>
                <img src="../../assets/images/펭수프로필.jpg" alt="" class="pengsu" v-b-toggle.sidebar-footer>
            </b-nav-item>
        </b-navbar-nav>
        
    </b-navbar>
    <b-sidebar id="sidebar-footer" aria-label="Sidebar with custom footer" no-header shadow>
        <template v-slot:footer="{ hide }">
        <div class="d-flex bg-dark text-light align-items-center px-3 py-2">
            <strong class="mr-auto">쌒편한세상</strong>
            <b-button size="sm" @click="hide">Close</b-button>
        </div>
        </template>
        <div class="px-3 py-2">
            <div class="thumbnail">
                <div class="centered">
                    <img src="../../assets/images/펭수프로필.jpg">
                </div>
            </div>
            <hr>
            <div>
                <ul>
                    <li class="text"><i class="far fa-list-alt"></i>&nbsp;내가 참여한 미션</li>
                    <li class="text"><i class="fas fa-user-edit"></i>&nbsp;내가 쓴 글</li>
                    <li class="text"><i class="fas fa-coins"></i>&nbsp;내 포인트</li>
                </ul>
            </div>
        </div>
    </b-sidebar>
    <br>
    </div>
</template>

<script>
export default {

}
</script>

<style scoped>
    .pengsu{
        width:50px;
        height:40px;
        margin-top:3px;
        margin-bottom:3px;
    }
    .btn{
        padding:0.125rem 0.25rem;
    }
    .thumbnail{
        position:relative;
        padding-top:100%; /*1:1 ratio */
        overflow:hidden;
    }
    .thumbnail .centered{
        position:absolute;
        top:0;
        left:0;
        right:0;
        bottom:0;
        -webkit-transform: translate(50%,50%); 
        -ms-transform: translate(50%,50%); 
        transform: translate(50%,50%);
        
    }
    .thumbnail .centered img { 
        position: absolute; 
        top: 0; 
        left: 0; 
        max-width: 100%; 
        height: 90%; 
        -webkit-transform: translate(-50%,-50%); 
        -ms-transform: translate(-50%,-50%); 
        transform: translate(-50%,-50%); 
        border-radius:50%;
        overflow: hidden;
    }
    .container > div{
        padding-top:10px;
    }
    .text{
        margin-bottom:20px;
    }
    .navbar{
        padding : 4px 8px;
    }
    a.nav-link{
        padding:0px;
    }
</style>
