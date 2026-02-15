import express from 'express';

const app = express();
const PORT = 3000;

const objResp = {
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com"
}

app.get('/', (req, res) => {
  res.json(objResp);
});

app.listen(PORT, () => {
  console.log(`Servidor rodando na porta ${PORT}`);
});