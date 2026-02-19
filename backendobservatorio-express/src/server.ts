import express from 'express';
import userRoutes from './module/user/user.routes.js';

const app = express();
const PORT = 3000;

app.use(express.json());

app.use("/users", userRoutes)

app.listen(PORT, () => {
  console.log(`Servidor rodando na porta ${PORT}`);
});