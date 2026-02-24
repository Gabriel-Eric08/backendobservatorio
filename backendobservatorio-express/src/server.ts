import express from 'express';
import userRoutes from './module/user/user.routes.js';
import datasetRoutes from './module/dataset/dataset.routes.js';
import swaggerUi from "swagger-ui-express";
import { swaggerSpec } from "./config/swagger.js";

const app = express();
const PORT = 3000;

app.use(express.json());

app.use("/users", userRoutes)
app.use("/datasets", datasetRoutes)

app.use("/docs", swaggerUi.serve, swaggerUi.setup(swaggerSpec));

app.listen(PORT, () => {
  console.log(`Servidor rodando na porta ${PORT}`);
});