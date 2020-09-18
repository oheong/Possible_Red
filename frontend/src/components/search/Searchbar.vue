<template>
  <div class="pt-0">
    <v-card color="red lighten-2" dark>
      <v-card-text>
      <v-autocomplete
          v-model="model"
          :loading="isLoading"
          :search-input.sync="search"
          color="white"
          hide-no-data
          hide-selected
          label=""
          placeholder="검색 방법 : 포스트내용, @사용자, #해시태그"
          prepend-icon="mdi-database-search"
          return-object
      ></v-autocomplete>
      </v-card-text>
    </v-card>
    <SearchCategoryBar v-if="!isSearching" />
  </div>
</template>

<script>
import SearchCategoryBar from "./SearchCategoryBar.vue";

  export default {
    props: {
      isSearching: Boolean
    },
    data() {
      return {
        descriptionLimit: 60,
        entries: [],
        isLoading: false,
        model: null,
        search: null,
        str: null,
        searchItem: {
          type: null,
          keyword: null,
        },
      }
    },
    components: {
      SearchCategoryBar,
    },
    methods: {
      
    },
    watch: {
      model (val) {
        if (val != null) this.tab = 0
        else this.tab = null
      },
      search (val) {
        if (this.search.length > 0) {
          this.searchItem.keyword = this.search
          
          if (this.search[0]=='@') {
            if (this.search.length >= 2){
            this.searchItem.keyword = this.search.substring(1);
            // console.log("uesr")
            this.searchItem.type="user"
            this.$emit('search-items', this.searchItem)
            } else {
              this.searchItem.type="none"
              this.searchItem.keyword = ""
              this.$emit('search-items', this.searchItem)
            }
          } else if (this.search[0]=="#"){
            if (this.search.length >= 2){
            this.searchItem.keyword = this.search.substring(1);
            // console.log("hashtag")
            this.searchItem.type="hashtag"
            this.$emit('search-items', this.searchItem)
            } else {
              this.searchItem.type="none"
              this.searchItem.keyword = ""
              this.$emit('search-items', this.searchItem)
            }
          } else {
            // console.log("post")
            this.searchItem.type="post"
            this.$emit('search-items', this.searchItem)
          }
        }
        else {
          this.searchItem.type="none"
          this.searchItem.keyword = ""
          this.$emit('search-items', this.searchItem)
        }
      },
    },
  }
</script>
<style scoped>
  .v-card__text{
    padding-top: 0;
    padding-bottom: 0;
  }
  .v-card__title.headline.red.lighten-3{
    color:white;
  }
  
  .v-card.v-sheet.theme--dark.red.lighten-2{
    background:linear-gradient(to bottom , #f48fb1, #3949ab) !important;
  }
  .v-text-field{
    margin-top: 0;
  }
  .search-space {
    margin: 0;
  }
  .jua{
    font-family: 'Jua', sans-serif;
  }
</style>