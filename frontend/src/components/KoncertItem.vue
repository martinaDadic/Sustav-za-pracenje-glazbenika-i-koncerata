<template>
  <div class="item">
    <div class="details">
      <h3>
        <slot name="naslov"></slot>
      </h3>
      <p>
        <slot name="artist"></slot>
      </p>
      <p>
        <slot name="datum"></slot>
      </p>
      <slot></slot>
    </div>
  </div>
</template>

<style scoped>
.item {
  border: 2px solid;
  border-radius: 10px;
  margin-bottom:1rem;
  padding-bottom: 1rem;
}

h3 {
  font-size: 1.2rem;
  font-weight: 5000;
}
</style>
