import styles from "../styles/navbar.module.css";
const Navbar = () => {
  return (
    <div className={styles.navbar}>
        <div className={styles.logo}>
            <span className={'material-symbols-outlined ' + styles.logoIcon}>Pancakes!</span>
            <a href="/" className={styles.logoText}>🎂</a>
        </div>
        <div className={styles.links}>
            <a href="/" className={styles.link}>Show orders</a>
            <a href="/create" className={styles.link}>Create order</a>
        </div>
    </div>
  );
}
export default Navbar;