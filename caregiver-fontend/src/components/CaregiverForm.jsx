export function CaregiverForm() {
    return (
        <div>
            <h2>Agregar Cuidador</h2>
            <form>
                <label>
                    Nombre del Cuidador:
                    <input type="text" placeholder="Nombre" />
                </label>
                <button type="submit">Guardar</button>
            </form>
        </div>
    );
}

